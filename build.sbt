name := """springHttpClientSample"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
	"org.springframework" % "spring-web" % "4.2.0.RELEASE",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.6.1",
	"junit" % "junit" % "4.12" % "test"
)
