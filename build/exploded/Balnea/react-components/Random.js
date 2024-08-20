'use strict';

class Random extends React.Component {
	constructor() {
		super();
		this.state = {
			result: 0,
		}
		this.onClick = this.onClick.bind(this);
	}
	
	onClick(e) {
		e.preventDefault();
		fetch('/DadoServlet', {
			method: "POST",
		}).then(response => {
			alert(response);
			this.setState({ result: 5 })
			return (<div>Ma come si fa {json}</div>);
		});

	}
	
	/*onClick() {
		const _url = "./DadoServlet";
		requestServerCalculation(_url, _number, _operation, (calculated_result) => {
				this.setState({ result: calculated_result })
			}
		);
	}
*/
	render() {
		let value;
		if (this.state.result === 0) {
			value = <i>Lancia il dado premendo qui sotto!</i>
		} else {
			value = <span>Il risultato del dado è: <i>{this.state.result}</i></span>;
		}
		return (
			<div id="ddd">
				<p>{value}</p>
				<button id="throw" onClick={this.onClick}>Lancia!</button>
			</div>
		)
	}
}