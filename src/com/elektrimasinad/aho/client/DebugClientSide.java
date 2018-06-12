package com.elektrimasinad.aho.client;

public class DebugClientSide {
private static boolean isEnabledFlag_ = false;
public static void enable() { isEnabledFlag_ = true; }
public static void setEnabled( final boolean isEnabledFlag )
{ isEnabledFlag_ = isEnabledFlag; }

public static void log( final String s )
{ if( isEnabledFlag_ ) nativeConsoleLogMehod( s ); }

private static native void nativeConsoleLogMehod( String s )
/*-{ console.log( s ); }-*/;
}