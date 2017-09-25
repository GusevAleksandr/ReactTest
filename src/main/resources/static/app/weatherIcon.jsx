import React from "react";

export class WeatherIcon extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
				<img src={'http://openweathermap.org/img/w/' + this.props.code + '.png'}/>
		)
	}
}