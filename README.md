# Mine2Coin

A Paper plugin that pays players in Vault economy rewards for mining configured blocks.

## Features

- Configurable rewards by block type
- Vault economy integration
- Simple block break listener with sound feedback

## Configuration

Edit `src/main/resources/config.yml` to map block materials to reward amounts:

```yml
coal_ore: 1
gold_ore: 3
```

## Requirements

- Paper 1.21.11+
- Vault
- A Vault-compatible economy plugin

## Build

```bash
mvn clean package
```
