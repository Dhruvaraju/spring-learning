# Spring Learning Related

## Adding a project to GitHub

### Adding a folder as a GitHub project

```shell
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/Dhruvaraju/spring-learning.git
git push -u origin main
```

- `git init` to initiate a folder as a git repository
- `git add README.md` adding a file named `README.md` to staging
- `git commit -m "first commit"` adding a first commit.
- `git branch -M main` creating a branch called `main`
- `git remote add origin https://github.com/Dhruvaraju/spring-learning.git` setting the remote repo to which this folder
  should go.
- `git push -u origin main` for pushing code to branch main

## Pushing an existing repository to GitHub

```shell
git remote add origin https://github.com/Dhruvaraju/spring-learning.git
git branch -M main
git push -u origin main
```