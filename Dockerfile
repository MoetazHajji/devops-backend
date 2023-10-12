FROM ubuntu:latest
LABEL authors="moetaz"

ENTRYPOINT ["top", "-b"]