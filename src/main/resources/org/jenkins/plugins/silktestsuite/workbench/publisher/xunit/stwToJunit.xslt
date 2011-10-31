<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	
	<xsl:template match="/">
    <xsl:element name="testsuite">
      <xsl:attribute name="name">SilkTest.Workbench.<xsl:value-of select="//Execution/Result/ResultEntry[1]/AssetName/@AssociatedProject"/>
      </xsl:attribute>
      <xsl:attribute name="tests">1</xsl:attribute>
      <xsl:attribute name="time">
        <xsl:value-of select="sum(//MilliSeconds) div 1000"/>
      </xsl:attribute>
      <xsl:element name="testcase">
        <xsl:attribute name="name">
          <xsl:value-of select="//Result/@name"/>
        </xsl:attribute>
        <xsl:attribute name="classname">SilkTest.Workbench.<xsl:value-of select="//Result/ResultEntry[1]/AssetName/@AssociatedProject"/></xsl:attribute>
        <xsl:attribute name="time">
          <xsl:value-of select="sum(//MilliSeconds) div 1000"/>
        </xsl:attribute>
        <xsl:apply-templates select="//ResultEntry/Result"/>
      </xsl:element>
      <xsl:element name="system-out">
        <xsl:apply-templates select="//ResultEntry"/>
      </xsl:element>
    </xsl:element>
  </xsl:template>
  
  <xsl:template match="//ResultEntry">
    [+<xsl:value-of select="./MilliSeconds"/>ms] <xsl:value-of select="./Actions"/> <xsl:value-of select="./Result"/>
    
  </xsl:template>
  
  <xsl:template match="//ResultEntry/Result">
    <xsl:choose>
      <xsl:when test="codepoint-equal(./@IntVal, '2')">
        <xsl:element name="failure">
          <xsl:attribute name="type"><xsl:value-of select="../ResultDetail"/></xsl:attribute>
          <xsl:attribute name="message"><xsl:value-of select="../Actions"/></xsl:attribute>
        </xsl:element>      
      </xsl:when>
      <xsl:when test="codepoint-equal(./@IntVal, '4')">
        <xsl:element name="error">
          <xsl:attribute name="type">Playback Error</xsl:attribute>
          <xsl:attribute name="message"><xsl:value-of select="../ResultDetail"/></xsl:attribute>
        </xsl:element>      
      </xsl:when>
    </xsl:choose>    
  </xsl:template>
</xsl:stylesheet>