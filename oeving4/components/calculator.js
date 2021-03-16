app.component('calculator', {
    template:
        `<div class="calculator" id="calculator">
            <div class="screen">{{ screen }}</div>
            <div v-on:click="clear();" class="button">C</div>
            <div v-on:click="addToScreen(this.answer + '')" class="button">ANS</div>
            <div v-on:click="del()" class="button">DEL</div>
            <div v-on:click="addToScreen('+')" class="button">+</div>
            <div v-on:click="addToScreen('1')" class="button">1</div>
            <div v-on:click="addToScreen('2')" class="button">2</div>
            <div v-on:click="addToScreen('3')" class="button">3</div>
            <div v-on:click="addToScreen('-')" class="button">-</div>
            <div v-on:click="addToScreen('4')" class="button">4</div>
            <div v-on:click="addToScreen('5')" class="button">5</div>
            <div v-on:click="addToScreen('6')" class="button">6</div>
            <div v-on:click="addToScreen('*')" class="button">*</div>
            <div v-on:click="addToScreen('7')" class="button">7</div>
            <div v-on:click="addToScreen('8')" class="button">8</div>
            <div v-on:click="addToScreen('9')" class="button">9</div>
            <div v-on:click="addToScreen('/')" class="button">/</div>
            <div class="button"></div>
            <div v-on:click="addToScreen('0')" class="button">0</div>
            <div v-on:click="addToScreen('.')" class="button">.</div>
            <div v-on:click="evaluate()" class="button">=</div>
        </div>`,
    data() {
        return {
            screen: '',
            answer: ''
        }
    },
    methods: {
        addToScreen(char) {
            if(char === '.') {
                if(isDecimalNumber(this.screen)) {
                    console.log('is decimal');
                    return;
                }
            }
            this.screen += char;
        },
        evaluate() {
            this.log(this.screen + ' = ' + eval(this.screen));
            this.screen = eval(this.screen) + '';
            this.answer = this.screen;
        },
        del() {
            this.screen = this.screen.substring(0, this.screen.length-1);
        },
        clear() {
            this.screen = '';
        },
        log(log) {
          this.$emit('log', log);
        }
    }
});

function isDecimalNumber(str) {
    let pos = str.length-1;
    if(pos === -1) return true;
    if(!isNumber(str.charAt(pos))) return true;
    pos--;
    while(pos > 0) {
        if(str.charAt(pos) === '.') return true;
        if(isChar(str.charAt(pos))) return false;
        pos--;
    }
}

function isNumber(char) {
    return ('1234567890').includes(char);
}

function isChar(char) {
    return ('+-*/').includes(char);
}