package com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionEncoder;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class RewardPointData extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b50604051620015d2380380620015d283398101806040528101908080518201929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506200009a336001620000ba6401000000000262000ea0179091906401000000009004565b8060059080519060200190620000b2929190620002cd565b50506200037c565b620000d58282620001a9640100000000026401000000009004565b1515156200014b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415151562000276576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f526f6c65733a206163636f756e7420697320746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200031057805160ff191683800117855562000341565b8280016001018555821562000341579182015b828111156200034057825182559160200191906001019062000323565b5b50905062000350919062000354565b5090565b6200037991905b80821115620003755760008160009055506001016200035b565b5090565b90565b611246806200038c6000396000f3006080604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806313af4035146100e057806320694db0146101235780633bbeaab51461016657806360df8a1f146101915780637b510fe8146101d6578063877b9a6714610279578063b2bdfa7b146102d4578063c510a9a81461032b578063c8e40fbf1461036e578063cd5d2118146103c9578063ce321bc514610424578063cfa84dfe146104c2578063d28eb96314610552578063e5c96aa414610595578063f8b2cb4f146105fc575b600080fd5b3480156100ec57600080fd5b50610121600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610694565b005b34801561012f57600080fd5b50610164600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610754565b005b34801561017257600080fd5b5061017b6107ae565b6040518082815260200191505060405180910390f35b34801561019d57600080fd5b506101bc600480360381019080803590602001909291905050506107b4565b604051808215151515815260200191505060405180910390f35b3480156101e257600080fd5b50610217600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610822565b604051808315151515815260200180602001828103825283818151815260200191508051906020019060200280838360005b83811015610264578082015181840152602081019050610249565b50505050905001935050505060405180910390f35b34801561028557600080fd5b506102ba600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610911565b604051808215151515815260200191505060405180910390f35b3480156102e057600080fd5b506102e961092e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561033757600080fd5b5061036c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610953565b005b34801561037a57600080fd5b506103af600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a50565b604051808215151515815260200191505060405180910390f35b3480156103d557600080fd5b5061040a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610aa6565b604051808215151515815260200191505060405180910390f35b34801561043057600080fd5b506104a8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290505050610b4d565b604051808215151515815260200191505060405180910390f35b3480156104ce57600080fd5b506104d7610c09565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156105175780820151818401526020810190506104fc565b50505050905090810190601f1680156105445780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561055e57600080fd5b50610593600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ca7565b005b3480156105a157600080fd5b506105e2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803515159060200190929190505050610d46565b604051808215151515815260200191505060405180910390f35b34801561060857600080fd5b5061063d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e05565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b83811015610680578082015181840152602081019050610665565b505050509050019250505060405180910390f35b61069d33610aa6565b1515610711576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b610768816001610ea090919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167f05e7c881d716bee8cb7ed92293133ba156704252439e5c502c277448f04e20c260405160405180910390a250565b60045481565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561081257600080fd5b8160048190555060019050919050565b60006060600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208080548060200260200160405190810160405280929190818152602001828054801561090157602002820191906000526020600020905b815460001916815260200190600101908083116108e9575b5050505050905091509150915091565b6000610927826001610f7d90919063ffffffff16565b9050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b61095c33610911565b15156109f6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001807f497373756572526f6c653a2063616c6c657220646f6573206e6f74206861766581526020017f207468652049737375657220726f6c650000000000000000000000000000000081525060400191505060405180910390fd5b610a0a8160016110a090919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167faf66545c919a3be306ee446d8f42a9558b5b022620df880517bc9593ec0f2d5260405160405180910390a250565b6000600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610ae55760019050610b48565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610b435760019050610b48565b600090505b919050565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610bab57600080fd5b81600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080519060200190610bfe9291906111a2565b506001905092915050565b60058054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c9f5780601f10610c7457610100808354040283529160200191610c9f56","5b820191906000526020600020905b815481529060010190602001808311610c8257829003601f168201915b505050505081565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610d0257600080fd5b80600660006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610da457600080fd5b81600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055506001905092915050565b6060600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020018280548015610e9457602002820191906000526020600020905b81546000191681526020019060010190808311610e7c575b50505050509050919050565b610eaa8282610f7d565b151515610f1f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515611049576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f526f6c65733a206163636f756e7420697320746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b6110aa8282610f7d565b1515611144576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260218152602001807f526f6c65733a206163636f756e7420646f6573206e6f74206861766520726f6c81526020017f650000000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60008260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b8280548282559060005260206000209081019282156111e4579160200282015b828111156111e35782518290600019169055916020019190600101906111c2565b5b5090506111f191906111f5565b5090565b61121791905b808211156112135760008160009055506001016111fb565b5090565b905600a165627a7a723058200092389436da0081ea89814f92103eb33a19f9c76f70e71acc6bcc864171c9640029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"60806040523480156200001157600080fd5b50604051620015d2380380620015d283398101806040528101908080518201929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506200009a336001620000ba6401000000000262000fc3179091906401000000009004565b8060059080519060200190620000b2929190620002cd565b50506200037c565b620000d58282620001a9640100000000026401000000009004565b1515156200014b576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415151562000276576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f526f6c65733a206163636f756e7420697320746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200031057805160ff191683800117855562000341565b8280016001018555821562000341579182015b828111156200034057825182559160200191906001019062000323565b5b50905062000350919062000354565b5090565b6200037991905b80821115620003755760008160009055506001016200035b565b5090565b90565b611246806200038c6000396000f3006080604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302afb286146100e057806305282c701461013b57806320a964d51461017e5780632392c0681461020e57806328e91489146102515780633f1baf84146102a857806354ca8d09146103405780636e0376d4146103a75780639d28a56c146104025780639e4ad09b14610445578063aaacab61146104a0578063be4f734b146104cb578063c269993a1461050e578063facb2ebf14610553578063fdf71de2146105f6575b600080fd5b3480156100ec57600080fd5b50610121600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610694565b604051808215151515815260200191505060405180910390f35b34801561014757600080fd5b5061017c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506106b1565b005b34801561018a57600080fd5b50610193610771565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101d35780820151818401526020810190506101b8565b50505050905090810190601f1680156102005780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561021a57600080fd5b5061024f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061080f565b005b34801561025d57600080fd5b50610266610869565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102b457600080fd5b506102e9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061088e565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561032c578082015181840152602081019050610311565b505050509050019250505060405180910390f35b34801561034c57600080fd5b5061038d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803515159060200190929190505050610929565b604051808215151515815260200191505060405180910390f35b3480156103b357600080fd5b506103e8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506109e8565b604051808215151515815260200191505060405180910390f35b34801561040e57600080fd5b50610443600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a8f565b005b34801561045157600080fd5b50610486600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b2e565b604051808215151515815260200191505060405180910390f35b3480156104ac57600080fd5b506104b5610b84565b6040518082815260200191505060405180910390f35b3480156104d757600080fd5b5061050c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b8a565b005b34801561051a57600080fd5b5061053960048036038101908080359060200190929190505050610c87565b604051808215151515815260200191505060405180910390f35b34801561055f57600080fd5b50610594600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610cf5565b604051808315151515815260200180602001828103825283818151815260200191508051906020019060200280838360005b838110156105e15780820151818401526020810190506105c6565b50505050905001935050505060405180910390f35b34801561060257600080fd5b5061067a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290505050610de4565b604051808215151515815260200191505060405180910390f35b60006106aa826001610ea090919063ffffffff16565b9050919050565b6106ba336109e8565b151561072e576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60058054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108075780601f106107dc57610100808354040283529160200191610807565b820191906000526020600020905b8154815290600101906020018083116107ea57829003601f168201915b505050505081565b610823816001610fc390919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167fd766f4a67950d691b1432c913f7a137a6566c6434f39026cc104656f3de99cdb60405160405180910390a250565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6060600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561091d57602002820191906000526020600020905b81546000191681526020019060010190808311610905575b50505050509050919050565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561098757600080fd5b81600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055506001905092915050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610a275760019050610a8a565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610a855760019050610a8a565b600090505b919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610aea57600080fd5b80600660006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60045481565b610b9333610694565b1515610c2d576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001807f497373756572526f6c653a2063616c6c657220646f6573206e6f74206861766581526020017f207468652049737375657220726f6c650000000000000000000000000000000081525060400191505060405180910390fd5b610c418160016110a090919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167f36b34ae98169cd7ef0173d334314715146307b58e670074b7fcb","08536203e19760405160405180910390a250565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610ce557600080fd5b8160048190555060019050919050565b60006060600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080805480602002602001604051908101604052809291908181526020018280548015610dd457602002820191906000526020600020905b81546000191681526020019060010190808311610dbc575b5050505050905091509150915091565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e4257600080fd5b81600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080519060200190610e959291906111a2565b506001905092915050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515610f6c576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f526f6c65733a206163636f756e7420697320746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b610fcd8282610ea0565b151515611042576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b6110aa8282610ea0565b1515611144576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260218152602001807f526f6c65733a206163636f756e7420646f6573206e6f74206861766520726f6c81526020017f650000000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60008260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b8280548282559060005260206000209081019282156111e4579160200282015b828111156111e35782518290600019169055916020019190600101906111c2565b5b5090506111f191906111f5565b5090565b61121791905b808211156112135760008160009055506001016111fb565b5090565b905600a165627a7a72305820aabdccc6a0c590616749992ffd13ef5c6b9bf2ffb9737d763e56c6a7431090770029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"addIssuer\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_totalAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"setTotalAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"getAccountInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"bytes32[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"isIssuer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"renounceIssuer\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"hasAccount\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"src\",\"type\":\"address\"}],\"name\":\"auth\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"bytes32[]\"}],\"name\":\"setBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_description\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"newVersion\",\"type\":\"address\"}],\"name\":\"upgradeVersion\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"b\",\"type\":\"bool\"}],\"name\":\"setAccount\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"getBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"description\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"account\",\"type\":\"address\"}],\"name\":\"IssuerAdded\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"account\",\"type\":\"address\"}],\"name\":\"IssuerRemoved\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC_ADDISSUER = "addIssuer";

    public static final String FUNC__TOTALAMOUNT = "_totalAmount";

    public static final String FUNC_SETTOTALAMOUNT = "setTotalAmount";

    public static final String FUNC_GETACCOUNTINFO = "getAccountInfo";

    public static final String FUNC_ISISSUER = "isIssuer";

    public static final String FUNC__OWNER = "_owner";

    public static final String FUNC_RENOUNCEISSUER = "renounceIssuer";

    public static final String FUNC_HASACCOUNT = "hasAccount";

    public static final String FUNC_AUTH = "auth";

    public static final String FUNC_SETBALANCE = "setBalance";

    public static final String FUNC__DESCRIPTION = "_description";

    public static final String FUNC_UPGRADEVERSION = "upgradeVersion";

    public static final String FUNC_SETACCOUNT = "setAccount";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final Event ISSUERADDED_EVENT = new Event("IssuerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event ISSUERREMOVED_EVENT = new Event("IssuerRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    protected RewardPointData(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt setOwner(String owner) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] setOwner(String owner, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(owner)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetOwner(String owner) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(owner)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getSetOwnerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt addIssuer(String account) {
        final Function function = new Function(
                FUNC_ADDISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] addIssuer(String account, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddIssuer(String account) {
        final Function function = new Function(
                FUNC_ADDISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getAddIssuerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDISSUER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public BigInteger _totalAmount() throws ContractException {
        final Function function = new Function(FUNC__TOTALAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt setTotalAmount(BigInteger amount) {
        final Function function = new Function(
                FUNC_SETTOTALAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] setTotalAmount(BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETTOTALAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetTotalAmount(BigInteger amount) {
        final Function function = new Function(
                FUNC_SETTOTALAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getSetTotalAmountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETTOTALAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getSetTotalAmountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETTOTALAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public Tuple2<Boolean, List<byte[]>> getAccountInfo(String account) throws ContractException {
        final Function function = new Function(FUNC_GETACCOUNTINFO, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<Boolean, List<byte[]>>(
                (Boolean) results.get(0).getValue(), 
                convertToNative((List<Bytes32>) results.get(1).getValue()));
    }

    public Boolean isIssuer(String account) throws ContractException {
        final Function function = new Function(FUNC_ISISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public String _owner() throws ContractException {
        final Function function = new Function(FUNC__OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt renounceIssuer(String account) {
        final Function function = new Function(
                FUNC_RENOUNCEISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] renounceIssuer(String account, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_RENOUNCEISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRenounceIssuer(String account) {
        final Function function = new Function(
                FUNC_RENOUNCEISSUER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getRenounceIssuerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_RENOUNCEISSUER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Boolean hasAccount(String account) throws ContractException {
        final Function function = new Function(FUNC_HASACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public Boolean auth(String src) throws ContractException {
        final Function function = new Function(FUNC_AUTH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(src)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt setBalance(String a, List<byte[]> value) {
        final Function function = new Function(
                FUNC_SETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(a), 
                value.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(value, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] setBalance(String a, List<byte[]> value, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(a), 
                value.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(value, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetBalance(String a, List<byte[]> value) {
        final Function function = new Function(
                FUNC_SETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(a), 
                value.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(value, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, List<byte[]>> getSetBalanceInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, List<byte[]>>(

                (String) results.get(0).getValue(), 
                convertToNative((List<Bytes32>) results.get(1).getValue())
                );
    }

    public Tuple1<Boolean> getSetBalanceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public String _description() throws ContractException {
        final Function function = new Function(FUNC__DESCRIPTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt upgradeVersion(String newVersion) {
        final Function function = new Function(
                FUNC_UPGRADEVERSION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(newVersion)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] upgradeVersion(String newVersion, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPGRADEVERSION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(newVersion)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUpgradeVersion(String newVersion) {
        final Function function = new Function(
                FUNC_UPGRADEVERSION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(newVersion)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getUpgradeVersionInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPGRADEVERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt setAccount(String a, Boolean b) {
        final Function function = new Function(
                FUNC_SETACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(a), 
                new org.fisco.bcos.sdk.abi.datatypes.Bool(b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] setAccount(String a, Boolean b, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(a), 
                new org.fisco.bcos.sdk.abi.datatypes.Bool(b)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetAccount(String a, Boolean b) {
        final Function function = new Function(
                FUNC_SETACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(a), 
                new org.fisco.bcos.sdk.abi.datatypes.Bool(b)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, Boolean> getSetAccountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, Boolean>(

                (String) results.get(0).getValue(), 
                (Boolean) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getSetAccountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public List getBalance(String account) throws ContractException {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        List<Type> result = (List<Type>) executeCallWithSingleValueReturn(function, List.class);
        return convertToNative(result);
    }

    public List<IssuerAddedEventResponse> getIssuerAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ISSUERADDED_EVENT, transactionReceipt);
        ArrayList<IssuerAddedEventResponse> responses = new ArrayList<IssuerAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IssuerAddedEventResponse typedResponse = new IssuerAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeIssuerAddedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(ISSUERADDED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeIssuerAddedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(ISSUERADDED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<IssuerRemovedEventResponse> getIssuerRemovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ISSUERREMOVED_EVENT, transactionReceipt);
        ArrayList<IssuerRemovedEventResponse> responses = new ArrayList<IssuerRemovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IssuerRemovedEventResponse typedResponse = new IssuerRemovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeIssuerRemovedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(ISSUERREMOVED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeIssuerRemovedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(ISSUERREMOVED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static RewardPointData load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new RewardPointData(contractAddress, client, credential);
    }

    public static RewardPointData deploy(Client client, CryptoKeyPair credential, String description) throws ContractException {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(description)));
        return deploy(RewardPointData.class, client, credential, getBinary(client.getCryptoSuite()), encodedConstructor);
    }

    public static class IssuerAddedEventResponse {
        public TransactionReceipt.Logs log;

        public String account;
    }

    public static class IssuerRemovedEventResponse {
        public TransactionReceipt.Logs log;

        public String account;
    }
}
