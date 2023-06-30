#!/bin/bash
docker build --platform linux/amd64 -t spring-receipt-processor-challenger .
docker run -p 8080:8080 -t spring-receipt-processor-challenger
