<template>
  <main>
    <section class="device-panel">
      <header>
        <h1>Data panel</h1>
      </header>

      <section class="device-info">
        <h3>
          General
        </h3>
        <p>Devices quantity: {{ devices.size }}</p>
      </section>

      <section class="device-info">
        <h3>
          Devices list
        </h3>
        <div
          v-for="[deviceId, device] in devices"
          :key="deviceId"
          class="device"
        >
          <p>Name: {{ device.name }}</p>
          <p>Type: {{ device.type.toLowerCase().trim() }}</p>
          <p>Unit: {{ device.unit }}</p>
        </div>
      </section>
    </section>

    <section class="chart-panel">
      <div class="chart">
        <line-chart :chart-data="temperatureChart" :options="chartOptions"></line-chart>
      </div>

      <div class="chart">
        <line-chart :chart-data="humidityChart" :options="chartOptions"></line-chart>
      </div>

      <div class="chart">
        <line-chart :chart-data="pressureChart" :options="chartOptions"></line-chart>
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
      dataCollection: {},
      temperatureChart: {},
      pressureChart: {},
      humidityChart: {},
      devices: new Map(),
      locations: new Map(),
      readings: new Map(),
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  created: function () {
    console.log('Websocket client init')
    const socket = new WebSocket('ws://localhost:3000')

    // Listen for messages
    socket.addEventListener('message', (event) => {
      const parsedMessage = JSON.parse(event.data)

      if (parsedMessage.messageType !== 'INFO') {
        const deviceEvent = JSON.parse(parsedMessage.payload)

        this.message = deviceEvent.device
        this.addDevice(deviceEvent.device)
        this.addLocation(deviceEvent.device.location)
        this.addReadings(deviceEvent)
      }
    })
  },
  methods: {
    addDevice (device) {
      this.devices = new Map(this.devices.set(device.id, device))
    },
    addLocation (location) {
      this.locations = new Map(this.locations.set(location.id, location))
    },
    addReadings (deviceEvent) {
      const deviceId = deviceEvent.device.id
      const reading = {
        id: deviceEvent.id,
        value: deviceEvent.value,
        readingUnit: deviceEvent.readingUnit,
        timestamp: deviceEvent.timestamp
      }

      const readings = this.readings.get(deviceId) || []
      readings.push(reading)
      readings.sort((firstReading, secondReading) => firstReading.date - secondReading.date)

      if (readings.length > 50) {
        readings.shift()
      }

      this.readings = new Map(this.readings.set(deviceId, readings))
      if (deviceEvent.device.unit === 'C') {
        this.temperatureChart = {
          labels: readings.map(reading => this.getFormattedTime(reading.timestamp)),
          datasets: [
            {
              label: `Temperature [${deviceEvent.readingUnit}]`,
              backgroundColor: '#f87979',
              data: readings.map(reading => Number(reading.value))
            }
          ]
        }
      } else if (deviceEvent.device.unit === 'hPa') {
        this.pressureChart = {
          labels: readings.map(reading => this.getFormattedTime(reading.timestamp)),
          datasets: [
            {
              label: `Pressure [${deviceEvent.readingUnit}]`,
              backgroundColor: '#2bac59',
              data: readings.map(reading => Number(reading.value))
            }
          ]
        }
      } else {
        this.humidityChart = {
          labels: readings.map(reading => this.getFormattedTime(reading.timestamp)),
          datasets: [
            {
              label: `Humidity [${deviceEvent.readingUnit}]`,
              backgroundColor: '#3aa9b8',
              data: readings.map(reading => Number(reading.value))
            }
          ]
        }
      }
    },
    getFormattedTime (timestamp) {
      return new Date(timestamp).toTimeString().slice(0, 9)
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
    width: 30%;
    border-right: 1px;
    background: linear-gradient(180deg, rgba(73,92,198,1) 0%, rgba(122,103,217,1) 100%);;

    header {
      margin: 20px 0 60px;

      h1 {
        width: 90%;
        margin: auto;
        padding: 15px 0;
        border: 2px solid #FFF;
        font-size: 60px;
        color: #FFF;
      }
    }

    h3 {
      width: 70%;
      margin: 0 auto 30px;
      padding: 10px 0;
      border: 1px solid #FFF;
      color: #FFF;
    }

    .device-info {
      margin: 0 auto 60px;

      p {
        margin: 20px 0;
        color: #FFF;
        font-size: 18px;
      }

      .device {
        width: 60%;
        margin: 10px auto;
        border-bottom: 1px solid #FFF;

        &:last-child {
          border-bottom: none;
        }
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
      height: 45%;
      min-width: 200px;
      min-height: 300px;
      max-height: 400px;
      border: 1px solid #000;
    }
  }
</style>
