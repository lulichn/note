#!/bin/sh

cd html

if type python3 >/dev/null 2>&1; then
	python3 -m http.server 8000
else
	python -m SimpleHTTPServer
fi

