#!/usr/bin/env bash

cf d -f fortune-service
cf d -f fortune-ui
cf ds -f circuit-breaker-dashboard
cf ds -f config-server
cf ds -f service-registry
cf ds -f fortunes-db
cf ds -f config-service
