import React, {Component} from 'react';
import Comp1 from './component1';
import Comp2 from 'C3DClassesSDK/component1';

class App extends Component {
    
	constructor() {
		super();
		this.state = {
			msg: "hi mannnnnnnnnnnnnnn"
		};
		this.isloading = true;
	}
	
	componentDidMount() {
		console.log("componentDidMount()");
		fetch("https://randomuser.me/api/?results=500").then(response =>response.json())
								.then(data=>console.log(data))
		
	}
	
	
	componentWillUnMount() {
		console.log("componentWillUnMount()");	
	}
	
	render() {
        return (
			<li>{this.isloading}?<h1>Loading....</h1>:<h1>Already loaded!!</h1>
			<div>
				<Comp1/>
				<Comp2/>
			</div>
			<div>
				<h1>{this.state.msg}</h1>
			</div>
			</li>
		)
    }
}

export default App;