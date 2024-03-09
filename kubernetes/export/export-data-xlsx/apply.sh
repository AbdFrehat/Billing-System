#!/bin/bash
kubectl apply -f=service.yaml -f=configmap.yaml -f=deployment.yaml  --namespace=export


