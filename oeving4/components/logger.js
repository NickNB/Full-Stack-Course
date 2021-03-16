app.component('logger', {
    props: ['logList']
    ,
    template:
    `
    <div class="log-box">
        <li v-for="log in logList">{{ log }}</li>
    </div>
    `,
    data() {
        return {
        }
    },
})