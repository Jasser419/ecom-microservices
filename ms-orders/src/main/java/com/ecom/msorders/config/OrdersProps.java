package com.ecom.msorders.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mes-config-ms")
public class OrdersProps {
    private int commandesLast = 10;

    public int getCommandesLast() { return commandesLast; }
    public void setCommandesLast(int commandesLast) { this.commandesLast = commandesLast; }
}

