if (process.env.NODE_ENV === "production") {
    const opt = require("./vilnius-pub-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./vilnius-pub-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./vilnius-pub-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
