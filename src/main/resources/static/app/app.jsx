import React from "react";
import ReactDom from "react-dom";
import $ from 'jquery';
import styles from '../style/style.css';

class WeatherIcon extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
				<img src={'http://openweathermap.org/img/w/' + this.props.code + '.png'}/>
		)
	}
}

class Card extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
				<div className={styles.childs}>

					<table>
						<caption>
							<h3>Дата:{this.props.date}</h3>
						</caption>
						<tr>
							<td>
								<div className={styles.info}>
									<label>Средняя температура:</label>
									<label>{this.props.temp}</label>
								</div>
								<div className={styles.info}>
									<label>Минимум:</label>
									<label>{this.props.minTemp}</label>
								</div>
								<div className={styles.info}>
									<label>Максимум:</label>
									<label>{this.props.maxTemp}</label>
								</div>
								<div className={styles.info}>
									<label>Влажость:{this.props.humidity}%</label>
									<label></label>
								</div>
							</td>
							<td>
								<WeatherIcon code={this.props.weather}/>
							</td>
						</tr>
					</table>
				</div>
		);
	}
}

class CardList extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
				<div id={styles.parent}>
					<div>
						<h2>Город: {this.props.city}</h2>
					</div>

					{this.props.list.filter(function (child) {
						return child.dt_txt.endsWith('12:00:00');
					}).map(function (child) {
						return (
								<Card temp={child.main.temp} minTemp={child.main.temp_min} maxTemp={child.main.temp_max}
								      date={child.dt_txt} weather={child.weather[0].icon} humidity={child.main.humidity} key={child.dt}/>
						)
					})}

				</div>
		);
	}
}

class NameForm extends React.Component {
	constructor(props) {
		super(props);
		this.state = {value: '', list: [], city: ''};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event) {
		this.setState({value: event.target.value});
	}

	handleSubmit(event) {

		$.ajax({
			url: '/testAjax2',
			dataType: 'json',
			cache: false,
			type: 'GET',
			data: {value1: this.state.value},
			success: function (data) {
				this.setState({list: data.list});
				this.setState({city: data.city.name});
			}.bind(this),
			error: function (xhr, status, err) {
				console.error(this.props.url, status, err.toString());
			}.bind(this)
		});
	}

	render() {
		return (
				<div>
					<label>
						Name:
						<input type="text" value={this.state.value} onChange={this.handleChange} />
					</label>

					<button onClick={this.handleSubmit}>sdd</button>
					<CardList list={this.state.list} city={this.state.city}/>
				</div>
		);
	}
}

ReactDom.render(<NameForm />, document.getElementById('react'));