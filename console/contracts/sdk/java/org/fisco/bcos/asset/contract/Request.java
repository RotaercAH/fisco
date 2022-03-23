package org.fisco.bcos.asset.contract;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Request extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040526000600260006101000a81548160ff021916908315150217905550336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610b898061006e6000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631e95c2ce1461007d5780632a00a472146100ae5780634fad18f1146101815780639c52a7f114610215578063b2bdfa7b14610258578063ff9913e8146102af575b600080fd5b34801561008957600080fd5b506100ac60048036038101908080356000191690602001909291905050506102f2565b005b3480156100ba57600080fd5b506100dd6004803603810190808035600019169060200190929190505050610522565b604051808560001916600019168152602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183151515158152602001828103825285818151815260200191508051906020019060200280838360005b8381101561016a57808201518184015260208101905061014f565b505050509050019550505050505060405180910390f35b34801561018d57600080fd5b50610213600480360381019080803560001916906020019092919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610687565b005b34801561022157600080fd5b50610256600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610862565b005b34801561026457600080fd5b5061026d610981565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102bb57600080fd5b506102f0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506109a6565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16148061039d575060011515600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b1515610411576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f4e6f742061757468656e7469636174656400000000000000000000000000000081525060200191505060405180910390fd5b806000191660036000836000191660001916815260200190815260200160002060000154600019161415156104ae576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f72657175657374206e6f7420666f756e6400000000000000000000000000000081525060200191505060405180910390fd5b6003600082600019166000191681526020019081526020016000206000808201600090556001820160006104e29190610ac4565b6002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160146101000a81549060ff0219169055505050565b6000606060008060006003600087600019166000191681526020019081526020016000209050856000191660036000886000191660001916815260200190815260200160002060000154600019161415156105e5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f72657175657374206e6f7420666f756e6400000000000000000000000000000081525060200191505060405180910390fd5b85816001018260020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168360020160149054906101000a900460ff168280548060200260200160405190810160405280929190818152602001828054801561067057602002820191906000526020600020905b81546000191681526020019060010190808311610658575b505050505092509450945094509450509193509193565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480610732575060011515600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b15156107a6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f4e6f742061757468656e7469636174656400000000000000000000000000000081525060200191505060405180910390fd5b8260036000856000191660001916815260200190815260200160002060000181600019169055508160036000856000191660001916815260200190815260200160002060010190805190602001906107ff929190610ae5565b508060036000856000191660001916815260200190815260200160002060020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610926576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610a6a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b5080546000825590600052602060002090810190610ae29190610b38565b50565b828054828255906000526020600020908101928215610b27579160200282015b82811115610b26578251829060001916905591602001919060010190610b05565b5b509050610b349190610b38565b5090565b610b5a91905b80821115610b56576000816000905550600101610b3e565b5090565b905600a165627a7a723058202164e091c1be5f7164074916dee883f4789a6ec15990d8ea61aec383e5f8a5cf0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"60806040526000600260006101000a81548160ff021916908315150217905550336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610b898061006e6000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806328e914891461007d57806356bea000146100d4578063766ecc04146101175780638f5c1106146101ea57806393878eed1461021b578063da89dd38146102af575b600080fd5b34801561008957600080fd5b506100926102f2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100e057600080fd5b50610115600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610317565b005b34801561012357600080fd5b506101466004803603810190808035600019169060200190929190505050610436565b604051808560001916600019168152602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183151515158152602001828103825285818151815260200191508051906020019060200280838360005b838110156101d35780820151818401526020810190506101b8565b505050509050019550505050505060405180910390f35b3480156101f657600080fd5b50610219600480360381019080803560001916906020019092919050505061059b565b005b34801561022757600080fd5b506102ad600480360381019080803560001916906020019092919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107cb565b005b3480156102bb57600080fd5b506102f0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506109a6565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156103db576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6000606060008060006003600087600019166000191681526020019081526020016000209050856000191660036000886000191660001916815260200190815260200160002060000154600019161415156104f9576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f72657175657374206e6f7420666f756e6400000000000000000000000000000081525060200191505060405180910390fd5b85816001018260020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168360020160149054906101000a900460ff168280548060200260200160405190810160405280929190818152602001828054801561058457602002820191906000526020600020905b8154600019168152602001906001019080831161056c575b505050505092509450945094509450509193509193565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480610646575060011515600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b15156106ba576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f4e6f742061757468656e7469636174656400000000000000000000000000000081525060200191505060405180910390fd5b80600019166003600083600019166000191681526020019081526020016000206000015460001916141515610757576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f72657175657374206e6f7420666f756e6400000000000000000000000000000081525060200191505060405180910390fd5b60036000826000191660001916815260200190815260200160002060008082016000905560018201600061078b9190610ac4565b6002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160146101000a81549060ff0219169055505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480610876575060011515600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b15156108ea576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f4e6f742061757468656e7469636174656400000000000000000000000000000081525060200191505060405180910390fd5b826003600085600019166000191681526020019081526020016000206000018160001916905550816003600085600019166000191681526020019081526020016000206001019080519060200190610943929190610ae5565b508060036000856000191660001916815260200190815260200160002060020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610a6a576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4e6f742061646d696e000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b5080546000825590600052602060002090810190610ae29190610b38565b50565b828054828255906000526020600020908101928215610b27579160200282015b82811115610b26578251829060001916905591602001919060010190610b05565b5b509050610b349190610b38565b5090565b610b5a91905b80821115610b56576000816000905550600101610b3e565b5090565b905600a165627a7a7230582033a65aac66e0672cec9412f8c6e08adfceb554cc63b728baeae330c982192bce0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"}],\"name\":\"deleteSaveRequest\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"}],\"name\":\"getRequestData\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"},{\"name\":\"SIG\",\"type\":\"bytes32[]\"},{\"name\":\"creator\",\"type\":\"address\"},{\"name\":\"passed\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"hash\",\"type\":\"bytes32\"},{\"name\":\"SIG\",\"type\":\"bytes32[]\"},{\"name\":\"creator\",\"type\":\"address\"}],\"name\":\"createSaveRequest\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"deny\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"allow\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_DELETESAVEREQUEST = "deleteSaveRequest";

    public static final String FUNC_GETREQUESTDATA = "getRequestData";

    public static final String FUNC_CREATESAVEREQUEST = "createSaveRequest";

    public static final String FUNC_DENY = "deny";

    public static final String FUNC__OWNER = "_owner";

    public static final String FUNC_ALLOW = "allow";

    protected Request(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt deleteSaveRequest(byte[] hash) {
        final Function function = new Function(
                FUNC_DELETESAVEREQUEST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] deleteSaveRequest(byte[] hash, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DELETESAVEREQUEST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDeleteSaveRequest(byte[] hash) {
        final Function function = new Function(
                FUNC_DELETESAVEREQUEST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getDeleteSaveRequestInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DELETESAVEREQUEST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public Tuple4<byte[], List<byte[]>, String, Boolean> getRequestData(byte[] hash) throws ContractException {
        final Function function = new Function(FUNC_GETREQUESTDATA, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<byte[], List<byte[]>, String, Boolean>(
                (byte[]) results.get(0).getValue(), 
                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                (String) results.get(2).getValue(), 
                (Boolean) results.get(3).getValue());
    }

    public TransactionReceipt createSaveRequest(byte[] hash, List<byte[]> SIG, String creator) {
        final Function function = new Function(
                FUNC_CREATESAVEREQUEST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                SIG.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(SIG, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(creator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] createSaveRequest(byte[] hash, List<byte[]> SIG, String creator, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATESAVEREQUEST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                SIG.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(SIG, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(creator)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateSaveRequest(byte[] hash, List<byte[]> SIG, String creator) {
        final Function function = new Function(
                FUNC_CREATESAVEREQUEST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash), 
                SIG.isEmpty()?org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]"):new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(SIG, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(creator)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<byte[], List<byte[]>, String> getCreateSaveRequestInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATESAVEREQUEST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<byte[], List<byte[]>, String>(

                (byte[]) results.get(0).getValue(), 
                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                (String) results.get(2).getValue()
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

    public static Request load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Request(contractAddress, client, credential);
    }

    public static Request deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Request.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
