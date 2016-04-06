#!/bin/bash

AMI=XXX
S3_BUCKET=mys3bucket

aws s3 cp hello.war s3://${S3_BUCKET}/hello.war
aws ec2 run-instances --image-id ${AMI} --count 5 --instance-type t2.micro --user-data install-my-app.sh
