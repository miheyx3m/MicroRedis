#!/bin/bash

echo '============================================================='
echo '$                                                           $'
echo '$                      Betc.io                              $'
echo '$  Test assignment                                          $'
echo '$  Mikhail Zotov                                            $'
echo '$  email:    zotov.mikhail85@gmail.com                      $'
echo '$                                                           $'
echo '============================================================='
echo '.'

mvn clean package

docker build -t betc.io/micro-redis .


