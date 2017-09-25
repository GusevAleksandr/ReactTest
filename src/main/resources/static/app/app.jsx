import React from "react";
import ReactDom from "react-dom";
import $ from 'jquery';
import 'antd/dist/antd.less';
import {Button, Input, Form, Layout} from 'antd';
const {Header, Footer, Sider, Content} = Layout;

import {CardList} from './cardList.jsx';
const FormItem = Form.Item;



class MainElement extends React.Component {
	constructor(props) {
		super(props);
		this.state = {value: '', list: [], city: '', windowHeight: window.innerHeight};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);

	}

	handleChange(event) {
		this.setState({value: event.target.value});
	}

	handleSubmit(event) {
		$.ajax({
			url: '/requestForecast',
			dataType: 'json',
			cache: false,
			type: 'GET',
			data: {city: this.state.value}, success: function (data) {
				this.setState({list: data.list});
				this.setState({city: data.city.name});
			}.bind(this),
			error: function (xhr, status, err) {
				alert(xhr.responseJSON.message);
			}.bind(this)
		});
	}

	render() {
		const minHeightConst = this.state.windowHeight;
		return (
				<div>
					<Layout style={{ minHeight: minHeightConst, height: '1px'}}>
						<Header style={{ textAlign: 'center'}}>
							<h2 style={{ color: 'white' }}>Получение прогноза погоды</h2>
						</Header>
						<Content style={{ padding: '0 50px', height: '100%' }}>
							<Form layout="inline" style={{ margin: 'auto', width: '20%', padding: '10px' }}>
								<FormItem required="true">
									<Input type="text" value={this.state.value} onChange={this.handleChange}
									       placeholder="Введите название города (английский)"/>
								</FormItem>
								<FormItem>
									<Button onClick={this.handleSubmit}>Получить прогноз</Button>
								</FormItem>
							</Form>
							<CardList list={this.state.list} city={this.state.city} />
						</Content>
						<Footer/>
					</Layout>

				</div>
		);
	}
}

ReactDom.render(<MainElement />, document.getElementById('react'));