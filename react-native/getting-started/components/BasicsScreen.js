import React, {Component} from "react";
import {StyleSheet, Text, View, Image} from "react-native";

export default class BasicsScreen extends Component {
    render() {
        let pic = {
            uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg'
        };
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
                <Text style={[styles.bigBlue, styles.red]}>Details Screen</Text>
                <Image source={pic} style={{width: 193, height: 110}}/>
                <Blink text='I love to blink' />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    bigBlue: {
        color: 'blue',
        fontWeight: 'bold',
        fontSize: 30,
    },
    red: {
        color: 'red',
    },
});

class Blink extends Component {

    componentDidMount(){
        // Toggle the state every second
        setInterval(() => (
            this.setState(previousState => (
                { isShowingText: !previousState.isShowingText }
            ))
        ), 1000);
    }

    //state object
    state = { isShowingText: true };

    render() {
        if (!this.state.isShowingText) {
            return null;
        }

        return (
            <Text>{this.props.text}</Text>
        );
    }
}