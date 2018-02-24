(defproject nasa-cmr/cmr-acl-lib "0.1.0-SNAPSHOT"
  :description "Contains utilities for retreiving and working with ACLs."
  :url "https://github.com/nasa/Common-Metadata-Repository/tree/master/acl-lib"
  :exclusions [
    [commons-io]
    [potemkin]]
  :dependencies [
    [commons-io "2.6"]
    [nasa-cmr/cmr-transmit-lib "0.1.0-SNAPSHOT"]
    [org.clojure/clojure "1.8.0"]
    [potemkin "0.4.4"]]
  :plugins [
    [lein-shell "0.5.0"]
    [test2junit "1.3.3"]]
  :jvm-opts ^:replace ["-server"
                       "-Dclojure.compiler.direct-linking=true"]
  :profiles {
    :dev {
      :exclusions [
        [org.clojure/tools.nrepl]]
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]
        [org.clojure/tools.nrepl "0.2.13"]
        [org.clojars.gjahad/debug-repl "0.3.3"]]
      :jvm-opts ^:replace ["-server"]
      :source-paths ["src" "dev" "test"]}
    :static {}
    ;; This profile is used for linting and static analysis. To run for this
    ;; project, use `lein lint` from inside the project directory. To run for
    ;; all projects at the same time, use the same command but from the top-
    ;; level directory.
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.2.5"]
        [lein-ancient "0.6.15"]
        [lein-bikeshed "0.5.0"]
        [lein-kibit "0.1.6"]
        [venantius/yagni "0.1.4"]]}
    ;; The following profile is overriden on the build server or in the user's
    ;; ~/.lein/profiles.clj file.
    :internal-repos {}}
  :aliases {;; Alias to test2junit for consistency with lein-test-out
            "test-out" ["test2junit"]
            ;; Linting aliases
            "kibit" ["do" ["with-profile" "lint" "shell" "echo" "== Kibit =="]
                          ["with-profile" "lint" "kibit"]]
            "eastwood" ["with-profile" "lint" "eastwood" "{:namespaces [:source-paths]}"]
            "bikeshed" ["with-profile" "lint" "bikeshed" "--max-line-length=100"]
            "yagni" ["with-profile" "lint" "yagni"]
            "check-deps" ["with-profile" "lint" "ancient" ":all"]
            "lint" ["do" ["check"] ["kibit"] ["eastwood"]]
            ;; Placeholder for future docs and enabler of top-level alias
            "generate-static" ["with-profile" "static" "shell" "echo"]})
