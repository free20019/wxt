title Œ¿–°Õ®
mode 200
@echo off
set clspath=.;wxt.jar
setlocal enabledelayedexpansion
for %%j in (".\lib\*.jar") do (
set clspath=!clspath!;%%j
)

java -cp %clspath% com.twkf.WxtApplication

pause