const app = Vue.createApp({
    data() {
        return {
            logList: []
        }
    },
    methods: {
        addToLog(log) {
            this.logList.unshift(log);
        }
    },
})