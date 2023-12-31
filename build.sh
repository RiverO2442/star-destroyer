#!/bin/bash

#./gradlew todo:bootJar
#docker build -t tiktzuki/todo:latest todo/
#docker push tiktzuki/todo:latest

#docker build -t tiktzuki/todo-web:latest todo/web/
#docker push tiktzuki/todo-web:latest

build_jar(){
  ./gradlew clean bootjar
}

build_docker() {
  docker build -t tiktuzki/start-destroyer:latest .
}

if [ "$1" = "build-jar" ] ; then
  build_jar
elif [ "$1" = "build-docker" ] ; then
  build_docker
# inspect arguments
#  while [ "$1" != "" ]; do
#    case $1 in
#      --webapps )    optionalComponentChosen=true
#                     classPath=$WEBAPPS_PATH,$classPath
#                     echo WebApps enabled
#                     ;;
#      --rest )       optionalComponentChosen=true
#                     restChosen=true
#                     classPath=$REST_PATH,$classPath
#                     echo REST API enabled
#                     ;;
#      --swaggerui )  optionalComponentChosen=true
#                     swaggeruiChosen=true
#                     classPath=$SWAGGER_PATH,$classPath
#                     echo Swagger UI enabled
#                     ;;
#      --example )    optionalComponentChosen=true
#                     classPath=$EXAMPLE_PATH,$classPath
#                     echo Invoice Example included - needs to be enabled in application configuration as well
#                     ;;
#      --production ) configuration=$PARENTDIR/configuration/production.yml
#                     productionChosen=true
#                     ;;
#      # the background flag shouldn't influence the optional component flags
#      --detached )   detachProcess=true
#                     echo Camunda Run will start in the background. Use the shutdown.sh script to stop it
#                     ;;
#      --help )       printf "%s" "$OPTIONS_HELP"
#                     exit 0
#                     ;;
#      * )            exit 1
#    esac
#    shift
#  done
elif [ "$1" = "" ] || [ "$1" = "help" ] ; then
  printf "Usage: build.sh [build-jar|build-docker] (options...) \n%s" "$OPTIONS_HELP"
fi