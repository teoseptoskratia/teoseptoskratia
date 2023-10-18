(ns teoseptoskratia.core-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [teoseptoskratia.core-test]))

(doo-tests 'teoseptoskratia.core-test)
