package org.jenkins.plugins.silktestsuite.classic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Level;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

final class JUnitXMLResultWriter implements Serializable {
  private static final long serialVersionUID = -5512544200585720228L;
  private PrintStream logger;
  private Document doc;
  private Element currentElement;
  private String resultPath;
  private String name;

  public JUnitXMLResultWriter(String resultPath, PrintStream logger) {
    this.logger = logger;
    this.resultPath = resultPath;
    this.doc = new Document();
  }
  
  public void addMessage(Level lvl, String message) {
    this.logger.println(MessageFormat.format("{0}: {1}", lvl, message));
    if (message.startsWith("Open Plan:") ||
        message.startsWith("Open Script:"))
      addTestSuite(message);
    else if (message.startsWith("Open Test Case:"))
      addTestCase(message);
    else if (message.startsWith("Log Error: "))
      addError(message);
    else if (message.startsWith("Print: "))
      addStdOut(message);
    else if (message.startsWith("Close Test Case: ") ||
        message.startsWith("Close Script: "))
      closeElement(message);
  }
  
  public void closeResult() {
    XMLOutputter xmlOut = new XMLOutputter();
    File result = new File(this.resultPath+"/"+this.name);
    OutputStream out = null;
    try {
      result.createNewFile();
      out = new BufferedOutputStream(new FileOutputStream(result));
      xmlOut.output(this.doc, out );
    } catch (IOException e) {
      this.logger.println(MessageFormat.format("ERROR: {0}", e.getMessage()));
    } finally {
      this.logger.println(MessageFormat.format("INFO: Write Resultfile (''{0}'').", result.getName()));
      try {
        if (out != null)
          out.close();
      } catch (IOException e) {
        this.logger.println(MessageFormat.format("ERROR: {0}", e.getMessage()));
      }      
    }
  }

  private void addTestSuite(String message) {
    Element newElement = new Element("testsuite");
    newElement.setAttribute("name", getTestSuiteName(message));
    newElement.setAttribute("startTime", String.valueOf(getTime(message)));
    newElement.setAttribute("tests", "0");
    newElement.setAttribute("failures", "0");
    newElement.setAttribute("errors", "0");
    newElement.setAttribute("skipped", "0");
    if (this.currentElement == null)
      this.doc.addContent(newElement);
    else
      this.currentElement.addContent(newElement);
    this.currentElement = newElement;
  }

  private void addTestCase(String message) {
    if (this.currentElement == null)
      addTestSuite("Open Plan: defaultTestSuite TIME=0");
    Element element = new Element("testcase");
    String testCaseName = getTestCaseName(message);
    element.setAttribute("name", testCaseName);
    element.setAttribute("classname", getClassName(testCaseName));
    element.setAttribute("startTime", String.valueOf(getTime(message)));
    this.currentElement.addContent(element);
    increaseCount(element.getParentElement(), "tests");
    
    this.currentElement = element;
  }

  private void addError(String message) {
    Element element = new Element("error");
    element.setAttribute("message", message);
    element.setAttribute("type", "Error");
    this.currentElement.addContent(element);
    increaseCount(this.currentElement.getParentElement(), "errors");
    
    element = element.getParentElement();
  }

  private void addStdOut(String message) {
    this.currentElement.addContent(message);
  }

  private void closeElement(String message) {
    long endTime = getTime(message);
    long startTime = Long.parseLong(this.currentElement.getAttribute("startTime").getValue());
    this.currentElement.setAttribute("time", String.valueOf(endTime-startTime));
    this.currentElement.removeAttribute("startTime");
    this.currentElement = this.currentElement.getParentElement();
  }

  private void increaseCount(Element element, String attribute) {
    while (element != null) {
      int count = Integer.parseInt(element.getAttribute(attribute).getValue());
      count++;
      element.setAttribute(attribute, String.valueOf(count));
      element = element.getParentElement();
    }
  }

  private void insertTestSuiteName(StringBuilder fullQualifiedName, Element elem) {
    fullQualifiedName.insert(0, ".");
    fullQualifiedName.insert(0, elem.getAttributeValue("name"));
  }

  private String getClassName(String testCaseName) {
    StringBuilder fullQualifiedName = new StringBuilder(testCaseName);
    Element elem = this.currentElement;
    while (elem.getParentElement() != null) {
      insertTestSuiteName(fullQualifiedName, elem);
      elem = elem.getParentElement();
    }
    return fullQualifiedName.toString();
  }

  private String getTestSuiteName(String message) {
    if (message.contains(".pln"))
      return getName(message, ".pln");
    else if (message.contains(".g.t"))
      return getName(message, ".g.t");
    else if (message.contains(".t"))
      return getName(message, ".t");
    else if (message.contains(".vtp"))
      return getName(message, ".vtp");
    else if (message.contains(".stp"))
      return getName(message, ".stp");
    else
      return "";
  }

  private String getTestCaseName(String message) {
    return getName(message, " ");
  }

  private String getName(String message, String suffix) {
    message.replace("\\", "/");
    int start = message.lastIndexOf("/");
    if (start == -1)
      start = message.indexOf(": ")+1;
    int end = message.indexOf(suffix, start+1);
    if (end == -1)
      end = message.indexOf(" TIME=", start+1);
    String str;
    if (end == -1)
      str = message.substring(start+1);
    else
      str = message.substring(start+1, end);
    
    if (this.name == null || this.name.equals(""))
      this.name = str+".xml";
    
    return str;
  }

  private long getTime(String message) {
    if (message.contains("TIME=")) {
      int startIdx = message.indexOf("TIME=")+5;
      int endIdx = message.indexOf(" ", startIdx);
      String str;
      if (endIdx == -1)
        str = message.substring(startIdx); 
      else
        str = message.substring(startIdx, endIdx);
      return Long.parseLong(str);
    } else
      return -1l;
  }
}
