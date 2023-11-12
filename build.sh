#./gradlew todo:bootJar
#docker build -t tiktzuki/todo:latest todo/
#docker push tiktzuki/todo:latest

docker build -t tiktzuki/todo-web:latest todo/web/
docker push tiktzuki/todo-web:latest