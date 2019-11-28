import React from 'react';
import {Button, View, Text} from 'react-native';
import {createAppContainer} from 'react-navigation';
import {createStackNavigator} from 'react-navigation-stack';
import {HomeScreen, BasicsScreen} from './components';


const AppNavigator = createStackNavigator({
        Home: HomeScreen,
        Basics: BasicsScreen,
    },
    {
        initialRouteName: 'Home',
    });

const AppContainer = createAppContainer(AppNavigator);

export default class App extends React.Component {
    render() {
        return <AppContainer/>;
    }
}