import React from 'react';
import {Button, Text, View} from 'react-native';
import * as InAppPurchases from 'expo-in-app-purchases';

export default class BasicScreen extends React.Component {
  message = 'foooooo';
  render() {
    return (
      <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
        <Text>InAppPurchase</Text>
        <Text>{this.message}</Text>

        <Button title="Purchase" onPress={() => this.press()} />
      </View>
    );
  }

  press() {
    const history = InAppPurchases.connectAsync().done();
    this.message = 'push';
  }
}
