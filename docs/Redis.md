---
id: redis
title: Redis
sidebar_label: Redis
---

## 型

### ドキュメント

* [Data types](https://redis.io/topics/data-types)

## Streams

### ドキュメント

* [Introduction to Redis Streams](https://redis.io/topics/streams-intro)
* [Real-time chat with Redis Streams](https://get-reddie.com/blog/building-real-time-chat-with-redis-streams/)

### コマンド

```
XADD <key> [MAXLEN <max-length>] <*/id> <field> <value>...

id: イベント追加ID（最新でなければならない）。現時点に追加するときは（*）を使用
max-length: オプション。最大長。ストリームを指定の長さに制限する
各設定フィールド
field name: フィールド名
value: 設定値
```

```
XREVRANGE <key> <end/+> <start/-> [COUNT <count>]

end: 読み出し開始ID （ストリーム先頭からには‘+‘を使用）)
start: 読み出し終了ID (ストリーム後尾までには‘-’を使用)
count: オプション。最大読み出しイベント数
```
