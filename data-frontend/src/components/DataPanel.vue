<template>
  <main>
    <section class="device-panel">
      <header>
        <h1>Data panel</h1>
      </header>

      <section class="devices-quantity">
        <h3>
          Devices quantity
        </h3>
        <p>4 {{message}}</p>
      </section>

      <section class="devices-list">
        <h3>
          Devices list
        </h3>
      </section>
    </section>

    <section class="chart-panel">
      <div class="chart">
        <div class="small">
          <line-chart :chart-data="dataCollection"></line-chart>
          <button @click="fillData()">Randomize</button>
        </div>
      </div>

      <div class="chart">

      </div>

      <div class="chart">

      </div>

      <div class="chart">

      </div>
    </section>
  </main>
</template>

<script>
import LineChart from './LineChart.js'

export default {
  name: 'DataPanel',
  components: {
    LineChart
  },
  data () {
    return {
      message: 'Unknown',
      dataCollection: null
    }
  },
  mounted () {
    this.fillData()
  },
  created: function () {
    console.log('Websocket client init')
    // const socket = new WebSocket('ws://localhost:3000')
    //
    // // Listen for messages
    // socket.addEventListener('message', (event) => {
    //   console.log(`Message from server: ${event.data}`)
    //   this.message = event.data
    // })
  },
  methods: {
    fillData () {
      this.dataCollection = {
        labels: [this.getRandomInt(), this.getRandomInt()],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#f87979',
            data: [this.getRandomInt(), this.getRandomInt()]
          }, {
            label: 'Data One',
            backgroundColor: '#f87979',
            data: [this.getRandomInt(), this.getRandomInt()]
          }
        ]
      }
    },
    getRandomInt () {
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5
    }
  }
}
</script>

<style scoped lang="scss">
  main {
    display: flex;
    min-height: 100vh;
  }

  .device-panel {
    width: 35%;
    border-right: 1px;
    background: linear-gradient(180deg, rgba(73,92,198,1) 0%, rgba(122,103,217,1) 100%);;

    h3 {
      color: #FFF;
    }

    header {
      margin: 20px 0 60px;

      h1 {
        color: #FFF;
      }
    }

    .devices-quantity {
      margin-bottom: 60px;

      p {
        margin-top: 10px;
        color: #FFF;
        font-weight: bold;
        font-size: 18px;
      }
    }
  }

  .chart-panel {
    display: flex;
    flex-wrap: wrap;
    align-content: space-around;
    justify-content: space-around;
    width: 65%;

    .chart {
      width: 45%;
      min-width: 200px;
      min-height: 300px;
      border: 1px solid #000;
    }
  }
</style>
