import React from 'react';
import {Button, View, Text} from 'react-native';
import {createAppContainer} from 'react-navigation';
import {createStackNavigator} from 'react-navigation-stack';
import {HomeScreen, BasicsScreen, Login, Purchase} from './components';

const AppNavigator = createStackNavigator(
  {
    Basics: BasicsScreen,
    Home: HomeScreen,
    Login: Login,
    Purchase: Purchase,
  },
  {
    initialRouteName: 'Basics',
  },
);

const AppContainer = createAppContainer(AppNavigator);

export default class App extends React.Component {
  render() {
    return <AppContainer />;
  }
}
