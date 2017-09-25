import React from "react";
import styles from '../style/style.css';

import { CardItem } from './card.jsx';

export class CardList extends React.Component {
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
								<CardItem temp={child.main.temp} minTemp={child.main.temp_min} maxTemp={child.main.temp_max}
								      date={child.dt_txt} weather={child.weather[0].icon} humidity={child.main.humidity} key={child.dt}/>
						)
					})}

				</div>
		);
	}
}