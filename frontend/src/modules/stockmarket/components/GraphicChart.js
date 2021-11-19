// install (please make sure versions match peerDependencies)
// yarn add @nivo/core @nivo/line
import { ResponsiveLine } from '@nivo/line'

// make sure parent container have a defined height when using
// responsive component, otherwise height will be 0 and
// no chart will be rendered.
// website examples showcase many properties,
// you'll often use just a few of them.
const GraphicChart = ({ data /* see data tab */ }) => (
    <ResponsiveLine
        data={data}
        margin={{ top: 50, right: 110, bottom: 115, left: 60 }}
		xScale={{ 
			type: "time",
            format: "%Y-%m-%d %H:%M:%S",
            precision: "second",
			useUTC:false
			}}
        xFormat="time:%HH:%MM:%SS"
		yScale={{ type: 'linear', min: 'auto', max: 'auto', stacked: true, reverse: false }}
        yFormat=" >-.2f"
        axisTop={null}
        axisRight={null}
        axisBottom={{
            format: "%Y-%m-%d %H:%M:%S",
            tickValues: "every 10 minutes",
            legend: "time scale",
            legendOffset: -12,
            orient: "bottom",
            tickValues: 15,
            tickSize: 5,
            tickPadding: 5,
            tickRotation: 45,
            legend: "",
            legendOffset: 36,
            legendPosition: "middle"
        }}
        axisLeft={{
            orient: 'left',
            tickSize: 5,
            tickPadding: 5,
            tickRotation: 0,
            legend: 'Price (€)',
            legendOffset: -40,
            legendPosition: 'middle'
        }}
		colors={{ scheme: 'category10' }}
        pointSize={10}
        pointColor={{ theme: 'background' }}
        pointBorderWidth={2}
        pointBorderColor={{ from: 'serieColor' }}
        pointLabelYOffset={-12}
        useMesh={true}
		enableArea={true}
        
    />
)

export default GraphicChart;