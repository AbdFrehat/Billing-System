#!/bin/bash
kubectl apply -f=service.yaml -f=pv.yaml -f=pvc.yaml -f=deployment.yaml  --namespace=data


