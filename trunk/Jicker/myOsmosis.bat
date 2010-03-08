set MYAPP_HOME=F:\geo\osmosis\osmosis-0.34
set MAINCLASS=org.openstreetmap.osmosis.core.Osmosis

rem SET OSMOSIS_OPTIONS=--read-xml file=F:\geo\map\germany.osm.bz2 enableDateParsing=no --wkv keyValueList="natural.coastline" --used-node --write-xml F:\geo\map\out.osm
SET OSMOSIS_OPTIONS=--read-xml file=F:\geo\map\germany.osm.bz2 enableDateParsing=no -bounding-box left=5.8 right=15.1 bottom=47.2 top=55.2 completeWays=no --write-xml F:\geo\map\out.osm

java -Xmx700m -cp %MYAPP_HOME%\osmosis.jar;%MYAPP_HOME%\lib\default\aopalliance-1.0.jar;%MYAPP_HOME%\lib\default\commons-compress-1.0.jar;%MYAPP_HOME%\lib\default\commons-dbcp-1.2.2.jar;%MYAPP_HOME%\lib\default\commons-logging-1.1.1.jar;%MYAPP_HOME%\lib\default\commons-pool-1.3.jar;%MYAPP_HOME%\lib\default\jpf-1.5.jar %MAINCLASS% %OSMOSIS_OPTIONS% 