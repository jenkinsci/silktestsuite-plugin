package com.borland.silktest.workbench.generated  ;

import com4j.COM4J;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  public static com.borland.silktest.workbench.generated.ISTWApp createSTWApp() {
    return COM4J.createInstance( com.borland.silktest.workbench.generated.ISTWApp.class, "{55E14DCF-65FB-4878-B16A-E29A53ABAD00}" );
  }

  public static com.borland.silktest.workbench.generated.ISTWPlaybackEnvironments createSTWPlaybackEnvironments() {
    return COM4J.createInstance( com.borland.silktest.workbench.generated.ISTWPlaybackEnvironments.class, "{9AA4E45B-2618-4726-B763-0E71E00F2CCF}" );
  }
}
