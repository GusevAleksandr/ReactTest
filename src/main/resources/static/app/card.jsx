import React from "react";
import styles from '../style/style.css';
import {WeatherIcon} from './weatherIcon.jsx';

import {Card} from 'antd';

export class CardItem extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
				<div className={styles.childs}>



					<Card title={this.props.date}>
						<table>
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
									</div>
								</td>
								<td>
									<WeatherIcon code={this.props.weather}/>
								</td>
							</tr>
						</table>
					</Card>
				</div>
		);
	}
}