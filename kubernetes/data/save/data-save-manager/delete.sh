#!/bin/bash
kubectl delete -f=service.yaml -f=configmap.yaml -f=deployment.yaml  --namespace=data


