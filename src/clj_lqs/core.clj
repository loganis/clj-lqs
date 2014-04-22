(ns clj-lqs.core
  "Clojure client for Loganis Query Script API"
  (:gen-class)
  (:require [org.httpkit.client :as http])
  (:require [clojure.data.json :as json])
  )

(def API-URL
  "https://ldash.loganis.com/LQS.aspx/api/v3")

(def re-token
  "Regular expression to validate token string"
  #"^[0-9a-fA-F]{64}$")

(defn check-params
  "Check input parameters. Returns an empty string if params are OK or an error message string"
  [token query]
  (cond
   (not (string? token)) "Token is not a string error."
   (not (map? query)) "Query is not a string error."
   (empty? token) "Token string is empty error."
   (empty? query) "Query string is empty error."
   (not (re-matches re-token token)) "Token is not valid error."
   (not (and
         (and 
          (contains? query :met)
          (string? (-> query :met))
          (not (empty? (-> query :met))))
         (or 
          (and
           (contains? query :per)
           (string? (-> query :per))
           (not (empty? (-> query :per))))
          (and 
           (and
            (contains? query :beg)
            (string? (-> query :beg))
            (not (empty? (-> query :beg))))
           (and 
            (contains? query :end)
            (string? (-> query :end))
            (not (empty? (-> query :end)))))))) "Query is not valid error."
   :else ""
   ))

(defn lqs->map
  "Loganis query, result is a map with :colnames and :rows keys."
  [token query]
  (let [check (check-params token query)]
    (if (empty? check)
      (let [res (http/post API-URL {:form-params 
                                    {:token token 
                                     :query (str (assoc query :fmt "jsz"))}})
            data (if (= 200 (-> @res :status)) (-> @res :body (json/read-str :key-fn keyword)))]
        data)
      {:colnames ["error"] :rows [[check]]})))

(defn lqs->csv
  "Loganis query, result is CSV formatted string with first row as column names header."
  [token query]
  (let [check (check-params token query)]
    (if (empty? check)
      (let [res (http/post API-URL {:form-params 
                                    {:token token 
                                     :query (str (assoc query :fmt "csz"))}})
            data (if (= 200 (-> @res :status)) (-> @res :body))]
        data)
      (str "error\n" check))))




(defn -main
  "Clojure client for Loganis Query Script API."
  []
  (println "Clojure client for Loganis Query Script API. Visit http://docs.loganis.com for more info."))
