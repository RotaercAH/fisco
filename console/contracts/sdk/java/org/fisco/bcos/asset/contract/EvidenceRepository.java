package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple8;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class EvidenceRepository extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610d46806100536000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806354f6127f1461007257806372edcbf2146102615780639c52a7f11461040b578063b2bdfa7b1461044e578063ff9913e8146104a5575b600080fd5b34801561007e57600080fd5b506100a160048036038101908080356000191690602001909291905050506104e8565b60405180896000191660001916815260200180602001806020018060200180602001806020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186810386528d818151815260200191508051906020019060200280838360005b8381101561013a57808201518184015260208101905061011f565b5050505090500186810385528c818151815260200191508051906020019060200280838360005b8381101561017c578082015181840152602081019050610161565b5050505090500186810384528b818151815260200191508051906020019060200280838360005b838110156101be5780820151818401526020810190506101a3565b5050505090500186810383528a818151815260200191508051906020019060200280838360005b838110156102005780820151818401526020810190506101e5565b50505050905001868103825289818151815260200191508051906020019060200280838360005b83811015610242578082015181840152602081019050610227565b505050509050019d505050505050505050505050505060405180910390f35b34801561026d57600080fd5b5061040960048036038101908080356000191690602001909291908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506107a4565b005b34801561041757600080fd5b5061044c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a40565b005b34801561045a57600080fd5b50610463610b5f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156104b157600080fd5b506104e6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b84565b005b600060608060608060606000806000600260008b6000191660001916815260200190815260200160002090508960001916816000015460001916141515610597576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f45766964656e6365206e6f74206578697374000000000000000000000000000081525060200191505060405180910390fd5b806000015481600101826002018360030184600401856005018660060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1687600701548680548060200260200160405190810160405280929190818152602001828054801561062957602002820191906000526020600020905b81546000191681526020019060010190808311610611575b505050505096508580548060200260200160405190810160405280929190818152602001828054801561067f57602002820191906000526020600020905b81546000191681526020019060010190808311610667575b50505050509550848054806020026020016040519081016040528092919081815260200182805480156106d557602002820191906000526020600020905b815460001916815260200190600101908083116106bd575b505050505094508380548060200260200160405190810160405280929190818152602001828054801561072b57602002820191906000526020600020905b81546000191681526020019060010190808311610713575b505050505093508280548060200260200160405190810160405280929190818152602001828054801561078157602002820191906000526020600020905b81546000191681526020019060010190808311610769575b505050505092509850985098509850985098509850985050919395975091939597565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16148061084f575060011515600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b15156108c3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f4e6f742061757468656e7469636174656400000000000000000000000000000081525060200191505060405180910390fd5b87600260008a60001916600019168152602001908152602001600020600001816000191690555086600260008a60001916600019168152602001908152602001600020600101908051906020019061091c929190610ca2565b5086600260008a60001916600019168152602001908152602001600020600201908051906020019061094f929190610ca2565b5084600260008a600019166000191681526020019081526020016000206003019080519060200190610982929190610ca2565b5082600260008a6000191660001916815260200190815260200160002060050190805190602001906109b5929190610ca2565b5081600260008a6000191660001916815260200190815260200160002060060160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600260008a60001916600019168152602001908152602001600020600701819055505050505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610b04576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610c48576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b828054828255906000526020600020908101928215610ce4579160200282015b82811115610ce3578251829060001916905591602001919060010190610cc2565b5b509050610cf19190610cf5565b5090565b610d1791905b80821115610d13576000816000905550600101610cfb565b5090565b905600a165627a7a7230582088590fdc4add3fcd9933ff99f05291966208c8cdc9600e907cec7b34de47b6640029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610d46806100536000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806328e914891461007257806356bea000146100c9578063b1fd28f01461010c578063cad1a469146102b6578063da89dd38146104a5575b600080fd5b34801561007e57600080fd5b506100876104e8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100d557600080fd5b5061010a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061050d565b005b34801561011857600080fd5b506102b460048036038101908080356000191690602001909291908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061062c565b005b3480156102c257600080fd5b506102e560048036038101908080356000191690602001909291905050506108c8565b60405180896000191660001916815260200180602001806020018060200180602001806020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186810386528d818151815260200191508051906020019060200280838360005b8381101561037e578082015181840152602081019050610363565b5050505090500186810385528c818151815260200191508051906020019060200280838360005b838110156103c05780820151818401526020810190506103a5565b5050505090500186810384528b818151815260200191508051906020019060200280838360005b838110156104025780820151818401526020810190506103e7565b5050505090500186810383528a818151815260200191508051906020019060200280838360005b83811015610444578082015181840152602081019050610429565b50505050905001868103825289818151815260200191508051906020019060200280838360005b8381101561048657808201518184015260208101905061046b565b505050509050019d505050505050505050505050505060405180910390f35b3480156104b157600080fd5b506104e6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b84565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156105d1576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614806106d7575060011515600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b151561074b576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f4e6f742061757468656e7469636174656400000000000000000000000000000081525060200191505060405180910390fd5b87600260008a60001916600019168152602001908152602001600020600001816000191690555086600260008a6000191660001916815260200190815260200160002060010190805190602001906107a4929190610ca2565b5086600260008a6000191660001916815260200190815260200160002060020190805190602001906107d7929190610ca2565b5084600260008a60001916600019168152602001908152602001600020600301908051906020019061080a929190610ca2565b5082600260008a60001916600019168152602001908152602001600020600501908051906020019061083d929190610ca2565b5081600260008a6000191660001916815260200190815260200160002060060160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600260008a60001916600019168152602001908152602001600020600701819055505050505050505050565b600060608060608060606000806000600260008b6000191660001916815260200190815260200160002090508960001916816000015460001916141515610977576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f45766964656e6365206e6f74206578697374000000000000000000000000000081525060200191505060405180910390fd5b806000015481600101826002018360030184600401856005018660060160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16876007015486805480602002602001604051908101604052809291908181526020018280548015610a0957602002820191906000526020600020905b815460001916815260200190600101908083116109f1575b5050505050965085805480602002602001604051908101604052809291908181526020018280548015610a5f57602002820191906000526020600020905b81546000191681526020019060010190808311610a47575b5050505050955084805480602002602001604051908101604052809291908181526020018280548015610ab557602002820191906000526020600020905b81546000191681526020019060010190808311610a9d575b5050505050945083805480602002602001604051908101604052809291908181526020018280548015610b0b57602002820191906000526020600020905b81546000191681526020019060010190808311610af3575b5050505050935082805480602002602001604051908101604052809291908181526020018280548015610b6157602002820191906000526020600020905b81546000191681526020019060010190808311610b49575b505050505092509850985098509850985098509850985050919395975091939597565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610c48576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b828054828255906000526020600020908101928215610ce4579160200282015b82811115610ce3578251829060001916905591602001919060010190610cc2565b5b509050610cf19190610cf5565b5090565b610d1791905b80821115610d13576000816000905550600101610cfb565b5090565b905600a165627a7a7230582051e72d9cf429fe370e2cb8e659b5a34dbe5f2cc228d35cf7c4f8c6d91216c18f0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"}],\"name\":\"getData\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"},{\"name\":\"FP\",\"type\":\"bytes32[]\"},{\"name\":\"RP\",\"type\":\"bytes32[]\"},{\"name\":\"Comm\",\"type\":\"bytes32[]\"},{\"name\":\"SIG\",\"type\":\"bytes32[]\"},{\"name\":\"Encript\",\"type\":\"bytes32[]\"},{\"name\":\"owner\",\"type\":\"address\"},{\"name\":\"timestamp\",\"type\":\"uint256\"}],\"name\":\"setData\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"deny\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"allow\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GETDATA = "getData";

    public static final String FUNC_SETDATA = "setData";

    public static final String FUNC_DENY = "deny";

    public static final String FUNC__OWNER = "_owner";

    public static final String FUNC_ALLOW = "allow";

    protected EvidenceRepository(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public Tuple8<byte[], List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, String, BigInteger> getData(byte[] hash) throws ContractException {
        final Function function = new Function(FUNC_GETDATA, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple8<byte[], List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, String, BigInteger>(
                (byte[]) results.get(0).getValue(), 
                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                convertToNative((List<Bytes32>) results.get(2).getValue()), 
                convertToNative((List<Bytes32>) results.get(3).getValue()), 
                convertToNative((List<Bytes32>) results.get(4).getValue()), 
                convertToNative((List<Bytes32>) results.get(5).getValue()), 
                (String) results.get(6).getValue(), 
                (BigInteger) results.get(7).getValue());
    }

    public TransactionReceipt setData(byte[] hash, List<byte[]> FP, List<byte[]> RP, List<byte[]> Comm, List<byte[]> SIG, List<byte[]> Encript, String owner, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_SETDATA, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                FP.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(FP, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                RP.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(RP, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                Comm.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(Comm, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                SIG.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(SIG, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                Encript.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(Encript, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(owner), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] setData(byte[] hash, List<byte[]> FP, List<byte[]> RP, List<byte[]> Comm, List<byte[]> SIG, List<byte[]> Encript, String owner, BigInteger timestamp, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETDATA, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                FP.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(FP, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                RP.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(RP, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                Comm.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(Comm, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                SIG.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(SIG, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                Encript.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(Encript, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(owner), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetData(byte[] hash, List<byte[]> FP, List<byte[]> RP, List<byte[]> Comm, List<byte[]> SIG, List<byte[]> Encript, String owner, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_SETDATA, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                FP.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(FP, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                RP.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(RP, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                Comm.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(Comm, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                SIG.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(SIG, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                Encript.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(Encript, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(owner), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple8<byte[], List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, String, BigInteger> getSetDataInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETDATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple8<byte[], List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, List<byte[]>, String, BigInteger>(

                (byte[]) results.get(0).getValue(), 
                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                convertToNative((List<Bytes32>) results.get(2).getValue()), 
                convertToNative((List<Bytes32>) results.get(3).getValue()), 
                convertToNative((List<Bytes32>) results.get(4).getValue()), 
                convertToNative((List<Bytes32>) results.get(5).getValue()), 
                (String) results.get(6).getValue(), 
                (BigInteger) results.get(7).getValue()
                );
    }

    public TransactionReceipt deny(String addr) {
        final Function function = new Function(
                FUNC_DENY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] deny(String addr, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DENY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDeny(String addr) {
        final Function function = new Function(
                FUNC_DENY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getDenyInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DENY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public String _owner() throws ContractException {
        final Function function = new Function(FUNC__OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt allow(String addr) {
        final Function function = new Function(
                FUNC_ALLOW, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] allow(String addr, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ALLOW, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAllow(String addr) {
        final Function function = new Function(
                FUNC_ALLOW, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getAllowInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ALLOW, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public static EvidenceRepository load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new EvidenceRepository(contractAddress, client, credential);
    }

    public static EvidenceRepository deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(EvidenceRepository.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
