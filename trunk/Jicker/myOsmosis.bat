set MYAPP_HOME=F:\geo\osmosis\osmosis
rem set MYAPP_HOME=F:\geo\osmosis\osmosis-0.35-SNAPSHOT-bin\osmosis-0.35-SNAPSHOT
rem set MYAPP_HOME=F:\geo\osmosis\osmosis-0.31-bin\osmosis-0.32
set MAINCLASS=org.openstreetmap.osmosis.core.Osmosis
rem set SAVEDIR=F:\geo\osmosis\osmosis-0.34
set PLEXUS_CP=%MYAPP_HOME%\lib\default\plexus-classworlds-2.2.2.jar
rem  set MAINCLASS=org.codehaus.classworlds.Launcher

rem SET OSMOSIS_OPTIONS=--read-xml file=F:\geo\map\germany.osm.bz2 enableDateParsing=no --wkv keyValueList="natural.coastline" --used-node --write-xml F:\geo\map\out.osm
rem SET OSMOSIS_OPTIONS=--read-xml file=F:\geo\map\germany.osm.bz2 enableDateParsing=no -bounding-box left=5.8 right=15.1 bottom=47.2 top=55.2 completeWays=no --write-xml F:\geo\map\out.osm
rem SET OSMOSIS_OPTIONS=--read-xml file=C:\gps_tmp\europe.osm.bz2 enableDateParsing=no -bounding-box left=5.8 right=15.1 bottom=47.2 top=55.2 completeWays=no --write-xml F:\geo\map\out.osm
SET OSMOSIS_OPTIONS=--read-xml enableDateParsing=no file=C:/gps_tmp/europe.osm.bz2 --bounding-box top=49.5138 left=10.9351 bottom=49.3866 right=11.201 completeWays=no --write-xml F:/geo/map/out.osm
java -Xmx700m -cp %MYAPP_HOME%\osmosis.jar;%MYAPP_HOME%\lib\default\aopalliance-1.0.jar;%MYAPP_HOME%\lib\default\commons-compress-1.0.jar;%MYAPP_HOME%\lib\default\commons-dbcp-1.2.2.jar;%MYAPP_HOME%\lib\default\commons-logging-1.1.1.jar;%MYAPP_HOME%\lib\default\commons-pool-1.3.jar;%MYAPP_HOME%\lib\default\jpf-1.5.jar %MAINCLASS% %OSMOSIS_OPTIONS%
rem java -cp %PLEXUS_CP% -Dapp.home=%MYAPP_HOME% -Dclassworlds.conf=%MYAPP_HOME%\config\plexus.conf %MAINCLASS%  %OSMOSIS_OPTIONS% %*