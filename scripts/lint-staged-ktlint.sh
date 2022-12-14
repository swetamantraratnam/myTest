#!/bin/bash
# Backpack for Android - Skyscanner's Design System
#
# Copyright 2018 Skyscanner Ltd
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


# Transforms the list of files provided by lint-staged into a format ktlint understands

ALL_FILES=$*

./gradlew ktlintCheck -Pfiles="$ALL_FILES"

if [ $? -ne 0 ]; then
  echo "Run './gradlew ktlintFormat' to fix issues"
  exit 1
fi
exit 0
