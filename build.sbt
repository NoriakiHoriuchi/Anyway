name := "anyway"

version := "1.0.0"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % Test

ghreleaseRepoOrg := "NoriakiHoriuchi"
ghreleaseRepoName := "Anyway"
ghreleaseNotes := (_ => "first release")
ghreleaseTitle := (_ => "first release")
ghreleaseIsPrerelease := (_ => false)