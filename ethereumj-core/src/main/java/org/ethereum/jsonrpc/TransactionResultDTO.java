package org.ethereum.jsonrpc;

import org.ethereum.core.Block;
import org.ethereum.core.Transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ruben on 8/1/2016.
 */
public class TransactionResultDTO {

    public String hash;					// DATA, 32 Bytes - hash of the transaction.
    public String nonce;				// QUANTITY - the number of transactions made by the sender prior to this one.
    public String blockHash;			// DATA, 32 Bytes - hash of the block where this transaction was in. null when its pending.
    public String blockNumber;			// QUANTITY - block number where this transaction was in. null when its pending.
    public String transactionIndex;		// QUANTITY - integer of the transactions index position in the block. null when its pending.

    public String from;					// DATA, 20 Bytes - address of the sender.
    public String to;					// DATA, 20 Bytes - address of the receiver. null when its a contract creation transaction.
    public String gas;					// QUANTITY - gas provided by the sender.
    public String gasPrice;				// QUANTITY - gas price provided by the sender in Wei.
    public String value;				// QUANTITY - value transferred in Wei.
    public String input;				// DATA - the data send along with the transaction.

    public TransactionResultDTO(@JsonProperty("hash") String hash, @JsonProperty("nonce") String nonce, 
    		@JsonProperty("blockHash") String blockHash, @JsonProperty("blockNumber") String blockNumber,
    		@JsonProperty("transactionIndex") String transactionIndex,
    		@JsonProperty("from") String from, @JsonProperty("to") String to, 
    		@JsonProperty("gas") String gas, @JsonProperty("gasPrice") String gasPrice, 
    		@JsonProperty("value") String value, @JsonProperty("input") String input) {
		this.hash = hash;
		this.nonce = nonce;
		this.blockHash = blockHash;
		this.blockNumber = blockNumber;
		this.transactionIndex = transactionIndex;
		this.from = from;
		this.to = to;
		this.gas = gas;
		this.gasPrice = gasPrice;
		this.value = value;
		this.input = input;
	}

	public TransactionResultDTO (Block b, int index, Transaction tx) {
        hash =  TypeConverter.toJsonHex(tx.getHash());
        nonce = TypeConverter.toJsonHex(tx.getNonce());
        blockHash = b == null ? null : TypeConverter.toJsonHex(b.getHash());
        blockNumber = b == null ? null : TypeConverter.toJsonHex(b.getNumber());
        transactionIndex = b == null ? null : TypeConverter.toJsonHex(index);
        from= TypeConverter.toJsonHex(tx.getSender());
        to = tx.getReceiveAddress() == null ? null : TypeConverter.toJsonHex(tx.getReceiveAddress());
        gas = TypeConverter.toJsonHex(tx.getGasLimit());
        gasPrice = TypeConverter.toJsonHex(tx.getGasPrice());
        value = TypeConverter.toJsonHex(tx.getValue());
        input  = tx.getData() != null ? TypeConverter.toJsonHex(tx.getData()) : null;
    }

    @Override
    public String toString() {
        return "TransactionResultDTO{" +
                "hash='" + hash + '\'' +
                ", nonce='" + nonce + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", transactionIndex='" + transactionIndex + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", gas='" + gas + '\'' +
                ", gasPrice='" + gasPrice + '\'' +
                ", value='" + value + '\'' +
                ", input='" + input + '\'' +
                '}';
    }
}