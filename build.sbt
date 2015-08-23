name := """springHttpClientSample"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
	"org.springframework" % "spring-web" % "4.2.0.RELEASE",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.6.1",
	"org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
