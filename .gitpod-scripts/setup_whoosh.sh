wget https://github.com/totogi/whoosh-java-releases/raw/main/whoosh-0.1.0-jar-with-dependencies.jar
mvn install:install-file \
   -Dfile=./whoosh-0.1.0-jar-with-dependencies.jar \
   -DgroupId=com.whoosh.sdk \
   -DartifactId=whoosh \
   -Dversion=0.1.0 \
   -Dpackaging=jar \
   -DgeneratePom=true
rm whoosh-0.1.0-jar-with-dependencies.jar
