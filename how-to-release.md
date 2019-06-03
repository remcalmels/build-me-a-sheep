# how-to-release



- on **master** branch, merge with develop :

```shell
git merge develop
```

- resolve conflicts, and commit :

```shell
git commit -m "master : git merge with develop"
```

- start releasing :

```shell
mvn --batch-mode release:prepare release:perform
```

- change the master pom.xml version to specify the **release version** (fe. 1.0)
- push all changes to GitHub :

```shell
git push && git push --tags
```

- delete **pom.xml.releaseBackup** and **release.properties** files :

```shell
git clean -fd
```

- checkout **develop**, change pom.xml version to *[{release_version}+0.1]-SNAPSHOT* and push



... you're now ready for the next coding ;)